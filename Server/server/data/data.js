var db = require("./db.js"),
    async = require('async');

exports.getString = function(callback){
    callback(null, "output");
}

exports.getcp1 = function(){
    var start, number, callback, ordervals, filterfieldvals;

    switch (arguments.length) {
      case 3:
        start = arguments[0];
        number = arguments[1];
        callback = arguments[2];
        break;
      case 4:
        start = arguments[0];
        number = arguments[1];
        ordervals = arguments[2];
        callback = arguments[3];
        break;
      case 5:
        filterfieldvals = arguments[0];
        start = arguments[1];
        number = arguments[2];
        ordervals = arguments[3];
        callback = arguments[4];
        break;
      default:
        throw new Error("This is not a valid use");
    }

    console.log("cp1.js");
    //return "Zamuel";
    
    var filter = filterfieldvals ? filterfieldvals : {};
    var output = [];
    var orderby = ordervals ? ordervals : { name : 1 };

    var cursor = db.cp1.find(filter);
    cursor.on("data", function (cp1) {
        output.push(cp1);
        //console.log("Animo: " + JSON.stringify(cp1));
        
    });
    cursor.once("end", function () {
        callback(null, output);
    });
}

exports.add_data = function (recipe_data, callback) {
    try {
        if (!recipe_data.SEXO) throw new Error("missing_sexo");
        if (!recipe_data.RANGOS) throw new Error("missing_ranges");
        if (!recipe_data.a2016) throw new Error("missing_year2016");
    } catch (e) {
        console.log("ERROOOOOOR");
        callback({ error: e.message, message: "This is not a valid data."});
    }


    async.waterfall([
        // get a unique id for this new recipe.
        function (cb) {
            get_unique_recipe_id(recipe_data, cb);
            console.log(recipe_data);
            db.cp1.insertOne(recipe_data, { w: 1 }, cb);
        }
        
    ], function (err, results) {
        callback(err, results);
    });
};

exports.get_recipe_by_id = function (recipe_id, callback) {
    var found_recipe = null;
    
    var cursor = db.recipes.find({ recipe_id: recipe_id }).limit(1);
    cursor.on("data", function (recipe) {
        found_recipe = recipe;
    });
    cursor.on("end", function () {
        console.log(JSON.stringify(found_recipe, null, 3));
        callback(null, found_recipe);
    });
};


/**
 * helper function to generate a recipe_id for us.
 */
function get_unique_recipe_id (recipe_data, callback) {
    if (!recipe_data.name) {
        return undefined;
    }

    var ok = false;

    var proposed_id = recipe_data.name.split(" ").join("_");

    async.doUntil(
        function (cb) {
            proposed_id += "" + (new Date().getTime());

            // only set this to true if we see a recipe!
            ok = true;
            var cursor = db.cp1.find({ recipe_id: proposed_id }).limit(1);
            cursor.on("data", function (recipe) {
                console.log("I got a recipe.....");
                if (recipe) {
                    ok = false;
                }
            });
            cursor.once("end", function () {
                console.log("Im done.....");
                cb(null);
            });
        },
        function () {
            console.log("QUeried about OK: " + ok);
            return ok;
        },
        function (err, results) {
            callback(err, proposed_id);
        });
    
};
