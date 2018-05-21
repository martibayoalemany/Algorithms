cmake_minimum_required(VERSION 3.2.2)

project(algorithms_main)

if (DEBUG)
    MESSAGE("Cmake building in debug mode")
else()
    MESSAGE("Cmake building in release mode")
endif()

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY  ${CMAKE_BINARY_DIR}/bin)

message("COMPILER_ID : " ${CMAKE_CXX_COMPILER_ID})

if ("${CMAKE_CXX_COMPILER_ID}" STREQUAL "Clang")        
    set(CMAKE_CXX_FLAGS " -g -O0 -Wall -std=c++14")
    MESSAGE("Using clang")
else()
    set(GCC_COVERAGE_COMPILE_FLAGS " -g -O0 -Wall -lpthread -std=c++1z -ggdb")
    MESSAGE("Not Using clang")
endif()

include(build/conanbuildinfo.cmake)
conan_basic_setup()

file(GLOB_RECURSE SOURCE_FILES src/*.cpp )
file(GLOB_RECURSE HEADER_FILES src/*.h )

foreach(var IN ITEMS ${CONAN_INCLUDE_DIRS})
    MESSAGE("${var}")
    include_directories(${var})
endforeach()

include_directories(${HEADER_FILES})
add_executable(algorithms_main basics/Sorting.cpp utils/Utils.cpp utils/Utils.h)
target_link_libraries(algorithms_main ${CONAN_LIBS})

