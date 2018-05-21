#!/usr/bin/env php
<?php

function rem_norm(/*float*/ $value) /*: int*/  {
        return round($value/2 - floor($value /2),0);
    } 
    
class binary_gap {
    /**
    * Returns the length of the biggest gap of zeros between two ones
    */
    public function execute(/*float*/ $value) /*:  int*/ {
        
        while (rem_norm($value) > 0) {
            $value = $value / 2;        
        }
        $currentGap = 0;
        $maxGap = 0;
        while ($value > 0) {
            $remainder = rem_norm($value) ;
            if ($remainder == 0) {
                $currentGap++;
            } 
            else if ($currentGap != 0) {
                $maxGap = ($currentGap > $maxGap) ? $currentGap : $maxGap;
                $currentGap = 0;
            }
            $value = $value / 2;
        }
        return $maxGap;
    }
}

$value = 654345;
$app = new binary_gap;
echo $app->execute(floatval($value));

?>
