#!/usr/bin/env node
'use strict'


function binary_gap(value) {
    
    while (value > 0 && (value % 2).toFixed(0) == 0)
        value = value / 2;        

    var currentGap = 0;
    var maxGap = 0;
    while (value.toFixed(0) > 0) {
        var remainder = value % 2 - value % 1;                
        if (remainder == 0) {
            currentGap++;
        } 
        else if (currentGap != 0) {
            maxGap = (currentGap > maxGap) ? currentGap : maxGap;
            currentGap = 0;
        }
        value = value / 2;
    }
    return maxGap;
}

var value = 654345
//console.warn("Binary Gap " + value + " " + value.toString(2));
//console.warn(binary_gap(value));
binary_gap(value);