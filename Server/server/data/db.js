var MongoClient = require('mongodb').MongoClient,
    ObjectId = require('mongodb').ObjectId,
    async = require('async'),
    assert = require('assert');

//ANDROID-----------------
var net = require('net');
var sockets = [];

var url = 'mongodb://Samuel:1234567890Ss@ds243008.mlab.com:43008/technological-society'

var _db;

exports.init_db = function (callback) {
    MongoClient.connect(url, function(err, db) {
        console.log("Connected correctly to MongoDB server.");

        _db = db;

        console.log("Current database", db.databaseName);

        // Got the connection, now get the data collection.
        exports.cp1                    = _db.collection("SciTech/HHRRBySexPeriod");
        exports.dataLogIn              = _db.collection("Users");
        exports.femaleResearch         = _db.collection("SciTech/FemaleResearchBySectorPeriod");
        callback(null);
    });
   
