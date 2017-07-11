#!/usr/bin/env node
'use strict'
// Slower in this scenario because of the inclusion of mathjs
var math = require('mathjs');

function binary_gap(value) { 
     
    while (value > 0 && math.mod(value, 2) == 0)
            value = value / 2;
   
    console.warn(value);   
    var currentGap = 0;
    var maxGap = 0;
    while (math.fix(value) > 0) {
        var remainder = math.fix(math.mod(value, 2));
                
        if (remainder == 0)
            currentGap++;        
        else if (currentGap != 0) {
            maxGap = math.max(currentGap, maxGap);            
            currentGap = 0;
        }
        value = value / 2;
    }
    return maxGap;
}

var value = 654345
// console.warn(binary_gap(value));
binary_gap(value);
