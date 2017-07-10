#!/usr/bin/env node
'use strict'

console.warn("Binary Gap");
console.warn(binary_gap(654345));

function binary_gap(value) { 
    console.warn(value);    
    while (value > 0 && (value % 2).toFixed(0) == 0)
            value = value / 2;
   
    var currentGap = 0;
    var maxGap = 0;
    while (value.toFixed(0) > 0) {
        var remainder = (value % 2).toFixed(0);
        if (remainder == 0) {
            currentGap++;
        } else if (currentGap != 0) {
            maxGap = (currentGap > maxGap) ? currentGap : maxGap;
            currentGap = 0;
        }
        value = value / 2;
    }
    return maxGap;
}