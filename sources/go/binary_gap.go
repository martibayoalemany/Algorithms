package main

import ("fmt",
     "math/Mod",
	 "math/Floor",
)

func  main() {
	fmt.Println(execute(654345))
}

func execute(value int) (result int) {
	//fmt.Println(value)
	for  mmod:=math.Mod(value, 2); value > 0 && mmod == 0;  {
            value := value / 2
	}

	currentGap := 0;
	var result int 
	maxGap := 0;
	for ;value > 0; {
		remainder := value % 2;
		if remainder == 0 {
			currentGap++;
		} else if currentGap != 0 {
			maxGap := Math.max(currentGap, maxGap);
			currentGap := 0
		}
		value := value / 2
	}
	result := maxGap
	return result;
}