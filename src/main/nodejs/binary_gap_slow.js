#!/usr/bin/env node
'use strict'
var math = require('mathjs');

//console.warn("Binary Gap");
//console.warn(binary_gap(654345));

function binary_gap(value) { 
    //console.warn(value);    
    while (value > 0 && math.mod(value, 2) == 0)
            value = value / 2;
   
    var currentGap = 0;
    var maxGap = 0;
    while (math.fix(value) > 0) {
        var remainder = math.fix(math.mod(value, 2));
        if (remainder == 0) {
            currentGap++;
        } else if (currentGap != 0) {
            maxGap = math.max(currentGap, maxGap);
            maxGap = math.max(currentGap, maxGap);
            currentGap = 0;
        }
        value = value / 2;
    }
    return maxGap;
}