'use strict';
const fs = require('fs');
const path = require('path');
const readline = require('readline-sync');

//To get the file size in Bytes
function getFilesizeInBytes(filename) {
    var stats = fs.statSync(filename)
    var fileSizeInBytes = stats["size"]
    return fileSizeInBytes
}

//To merge two JSON objects and return a single object
var merge = function() {
var destination = {},
    sources = [].slice.call( arguments, 0 );
sources.forEach(function( source ) {
    var prop;
    for ( prop in source ) {
        if ( prop in destination && Array.isArray( destination[ prop ] ) ) {

            // Concat Arrays
            destination[ prop ] = destination[ prop ].concat( source[ prop ] );

        } else if ( prop in destination && typeof destination[ prop ] === "object" ) {

            // Merge Objects
            destination[ prop ] = merge( destination[ prop ], source[ prop ] );

        } else {

            // Set new values
            destination[ prop ] = source[ prop ];

        }
    }
});
return destination;
};

//Getting the input-->path to folder, input basefilename, outputbasefilename, maxfilesize
var testFolder = readline.question("Enter the path to the folder:");

var inputBaseFileName = readline.question("Enter the input Base File Name:");

var outputBaseFileName = readline.question("Enter the output Base File Name:");

var maxFileSize = readline.question("Enter the max File Size:");


var rawdata1 = null
var data1 = null 
var rawdata2 = null
var data2 = null
var counter = 1


var outputFileName = testFolder+outputBaseFileName+counter+".json"

fs.readdir(testFolder, (err, files) => {
   fs.appendFileSync(outputFileName,fs.readFileSync(testFolder+files[1]))
  files.forEach(file => {
    if(file.localeCompare('.DS_Store') != 0 && files[1].localeCompare(file) != 0){
        if(file.indexOf(inputBaseFileName) != -1){
            if(getFilesizeInBytes(outputFileName)+getFilesizeInBytes(testFolder+file) < maxFileSize){
                rawdata1 = fs.readFileSync(outputFileName);
                data1 = JSON.parse(rawdata1);
                rawdata2 = fs.readFileSync(testFolder+file);
                data2 = JSON.parse(rawdata2);
                var json = JSON.stringify(merge(data1, data2));
                if(counter == 1)
                    fs.writeFileSync(outputFileName,json);
                else
                    fs.appendFileSync(outputFileName, json);
            }
            else{
                counter= counter+1
                outputFileName = testFolder+outputBaseFileName+counter+".json"
                rawdata2 = fs.readFileSync(testFolder+file);
                fs.appendFileSync(outputFileName, rawdata2);
            }
        }
    }
  });
});
