var MongoClient = require('mongodb').MongoClient,
    ObjectId = require('mongodb').ObjectId,
    async = require('async'),
    assert = require('assert');

//ANDROID-----------------
var net = require('net');
var sockets = [];

//var url = 'mongodb://samuel:samuel88@ds243008.mlab.com:43008/tfgddbb';
var url = 'mongodb://Samuel:1234567890Ss@ds243008.mlab.com:43008/technological-society'

var _db;

exports.init_db = function (callback) {
    MongoClient.connect(url, function(err, db) {
        console.log("Connected correctly to MongoDB server.");

        _db = db;

        console.log("Current database", db.databaseName);

        // Got the connection, now get the recipes collection. It's easy.
        exports.cp1 = _db.collection("HHRRBySexPeriod");
        callback(null);
    });

    //ANDROID-------------------------

    /*var svr = net.createServer(function(sock) {
        console.log('Connected: ' + sock.remoteAddress + ':' + sock.remotePort);
        sockets.push(sock);
     
        sock.write('Welcome to the server!\n');
     
        sock.on('data', function(data) {
            for (var i=0; i<sockets.length ; i++) {
                if (sockets[i] != sock) {
                    if (sockets[i]) {
                        sockets[i].write(data);
                    }
                }
            }
        });
     
        sock.on('end', function() {
            console.log('Disconnected: ' + sock.remoteAddress + ':' + sock.remotePort);
            var idx = sockets.indexOf(sock);
            if (idx != -1) {
                delete sockets[idx];
            }
        });
    });
 
    var svraddr = '192.168.0.29';
    var svrport = 8083;
     
    svr.listen(svrport, svraddr);
    console.log('Server Created at ' + svraddr + ':' + svrport + '\n');*/

}   