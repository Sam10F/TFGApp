var db = require("./db.js"),
    async = require('async'),
    bcrypt = require('bcrypt');

exports.get_female_research = function(){
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
    
    var filter = filterfieldvals ? filterfieldvals : {};
    var output = [];
    var orderby = ordervals ? ordervals : { name : 1 };

    var cursor = db.femaleResearch.find(filter);
    cursor.on("data", function (femaleResearch) {
        output.push(femaleResearch);        
    });
    cursor.once("end", function () {

        callback(null, output);
    });
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
    
    var filter = filterfieldvals ? filterfieldvals : {};
    var output = [];
    var orderby = ordervals ? ordervals : { name : 1 };

    var cursor = db.cp1.find(filter);
    cursor.on("data", function (cp1) {
        output.push(cp1);        
    });
    cursor.once("end", function () {

        callback(null, output);
    });
}

exports.edit = function(dataEdit, callback){
    var pass = bcrypt.hashSync(dataEdit[2], 10);

    console.log(dataEdit[3]);

    var myquery = { username: dataEdit[3] };
    var newvalues = { $set: { email: dataEdit[0], username: dataEdit[1], password: pass } };

    db.dataLogIn.update(myquery, newvalues, function(err, res){
        if(err == null){
            console.log("1 document inserted");
            callback(null, "User profile edited");
        }else if(err.message.includes("technological-society.Users.$username_1")){
            callback("User name already in use");
        }else if(err.message.includes("technological-society.Users.$email_1")){
            callback("Mail already in use");
        }
    });
    
}

exports.signUp = function(dataSignUp, callback){

    var result = [];
    var pass = bcrypt.hashSync(dataSignUp[2], 10);
    result.push({
                    email:      dataSignUp[0],
                    username:   dataSignUp[1],
                    password:   pass,
                    signupDate: new Date(Date.now()) 
                });

    console.log(result);

    db.dataLogIn.insert(result, function(err, res){
        if(err == null){
            console.log("1 document inserted");
            callback(null, "Welcome new user, " + dataSignUp[1]);
        }else if(err.message.includes("technological-society.Users.$username_1")){
            callback("User name already in use");
        }else if(err.message.includes("technological-society.Users.$email_1")){
            callback("Mail already in use");
        }
    });
}

exports.logIn = function(dataLogIn, callback){
    var success = false;
    var usrData;


    var cursor = db.dataLogIn.find({ "username": dataLogIn[0] }).limit(1);
    cursor.on("data", function (data) {
        //console.log(data.password);
        success = bcrypt.compareSync(dataLogIn[1], data.password);
        var usr = [data.username, data.email, data.password]
        usrData = usr;
    });
    cursor.once("end", function () {
        console.log("h: " + usrData[1]);
        success ? callback(null, usrData) : callback("false");
    });
}

exports.add_data = function (data_data, callback) {

    async.waterfall([
        // get a unique id for this new data.
        function (cb) {
            get_unique_data_id(data_data, cb);
            //db.cp1.insertOne(data_data, { w: 1 }, cb);
        }
        
    ], function (err, results) {
        callback(err, results);
    });

};

exports.get_data_by_id = function (data_id, callback) {
    var found_data = null;
    
    var cursor = db.datas.find({ data_id: data_id }).limit(1);
    cursor.on("data", function (data) {
        found_data = data;
        console.log("found: " + found_data);

    });
    cursor.on("end", function () {
        console.log(JSON.stringify(found_data, null, 3));
        callback(null, found_data);
    });
};


/**
 * helper function to generate a data_id for us.
 */
function get_unique_data_id (data_data, callback) {
    if (!data_data.name) {
        return undefined;
    }

    var ok = false;

    var proposed_id = data_data.name.split(" ").join("_");

    async.doUntil(
        function (cb) {
            proposed_id += "" + (new Date().getTime());

            // only set this to true if we see a data!
            ok = true;
            var cursor = db.cp1.find({ data_id: proposed_id }).limit(1);
            cursor.on("data", function (data) {
                console.log("I got a data.....");
                if (data) {
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
