#!/usr/bin/env perl
use strict;

print &binary_gap(654345);

sub binary_gap { 
    my $value = shift;
       
    $value/=2 while ($value > 0 && $value % 2 == 0);
            
    my $currentGap = 0;
    my $maxGap = 0;
    while (int($value) > 0) {
        my $remainder = int($value % 2);
        if ($remainder == 0) {
            $currentGap++;
        } elsif ($currentGap != 0) {
            $maxGap = $maxGap < $currentGap ? $currentGap : $maxGap;
            $currentGap = 0;
        }
        $value/=2;
    }
    $maxGap;
}
