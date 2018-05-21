cmake_minimum_required(VERSION 3.2.2)
project(c_algorithms)

set (CMAKE_CXX_FLAGS "-lm")
add_executable(BinaryGap BinaryGap.c)
target_link_libraries(BinaryGap m)