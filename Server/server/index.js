var express = require('express'),
    bodyParser = require('body-parser'),
    morgan = require('morgan'),
    data_handler = require("./handlers/myhandler.js"),
    db = require("./data/db.js"),
    async = require("async"),
    jwt = require('jwt-simple');



var _port = 8082;
var app = express();


app.use(morgan('dev'));
app.use(bodyParser.urlencoded({
  extended: true
}));

app.use(bodyParser.json());
app.use(express.static(__dirname + "/../static"));

app.get("/v1/data.json", function (req, res) {
    var start = req.query.start ? parseInt(req.query.start) : 0;
    var pageSize = req.query.pageSize ? parseInt(req.query.pageSize) : 100;

    data_handler.getcp1(start, pageSize, function (err, data) {
        if (err) {
            return send_error_resp(res, err);
        } else {
            return send_success_resp(res, data);
        }
    });
});

app.post("/v1/login.json", function(req, res){

    console.log("Loging up...");

    var str = JSON.parse(Object.keys(req.body)[0]);
    var decoded = jwt.decode(str["loginToken"], "secret");
    var dataLogIn = [decoded["usr"], decoded["pswd"]];

    data_handler.logIn(dataLogIn, function(err, recipe) {
        if (err) {
            console.log("Login Incorrecto...");
            return send_error_resp(res, err);
        } else {
            console.log("Login Correcto...");
            console.log("EJE: " + recipe[1]);
            return send_success_resp(res, recipe);
        }
    });

});

app.post("/v1/edit.json", function(req, res){

    console.log("Editing...");

    var str = JSON.parse(Object.keys(req.body)[0]);
    var decoded = jwt.decode(str["signupToken"], "secret");
    var dataEdit = [decoded["mail"], decoded["usr"], decoded["pswd"], decoded["oldUsrName"]];

    data_handler.edit(dataEdit, function(err, recipe) {
        if (err) {
            return send_error_resp(res, err);
        } else {
            return send_success_resp(res, recipe);
        }
    });

});

app.post("/v1/signup.json", function(req, res){

    console.log("Singing up...");

    var str = JSON.parse(Object.keys(req.body)[0]);
    var decoded = jwt.decode(str["signupToken"], "secret");
    var dataSignUp = [decoded["mail"], decoded["usr"], decoded["pswd"]];

    data_handler.signUp(dataSignUp, function(err, recipe) {
        if (err) {
            return send_error_resp(res, err);
        } else {
            return send_success_resp(res, recipe);
        }
    });

});


db.init_db(function (err) {
    if (err) {
        console.log("Error initialising DB, aborting: " + JSON.stringify(err, 0, 2));
        exit(-1);
    } else {
        console.error("Starting Server.");
        app.listen(_port);
    }
});



/**
 * res, http_status, code, message
 * res, http_status, err obj
 * res, err obj
 */
function send_error_resp(res, err) {
    res.setHeader('Content-Type', 'application1/json');
    res.status(400).send(JSON.stringify(err));
    res.end();
}

function send_success_resp(res, obj) {
    res.setHeader('Content-Type', 'application2/json');
    res.status(200).send(JSON.stringify(obj));
    res.end();
}


function _http_code_from_error (error_code) {
    switch (error_code) {
      // add other messages here when they're not server problems.
      default:
        return 503;
    }
}
