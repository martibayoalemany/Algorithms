#!/usr/bin/env perl6
#use strict;

say &binary_gap(654345);

sub binary_gap(Int $param=654345) returns Int { 
    my $value = $param;
    $value/=2 while ($value > 0 && $value % 2 == 0);
            
    my $currentGap = 0;
    my $maxGap = 0;
    while ($value.Int > 0) {
        my $remainder = ($value % 2).Int;
        if ($remainder == 0) {
            $currentGap++;
        } elsif ($currentGap != 0) {
            $maxGap = $maxGap < $currentGap ?? $currentGap !! $maxGap;
            $currentGap = 0;
        }
        $value/=2;
    }
    return $maxGap;
}
