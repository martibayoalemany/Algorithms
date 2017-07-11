#!/usr/bin/env octave-cli

function result = execute(value)
    while value > 0 & mod(value,2) == 0 
       value = value / 2   
    end

    currentGap = 0
    maxGap = 0
    while floor(value) > 0  
        remainder = floor(mod(value,2)) 
        if remainder == 0
            currentGap++
        elseif currentGap != 0
            maxGap = max(currentGap,maxGap)
            currentGap = 0;
        end
        value=value / 2; 
    end
    result = maxGap
end

value=execute(654345)
fprintf('%d ',value)