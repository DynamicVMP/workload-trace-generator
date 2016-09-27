#!/bin/bash

# Creates necessary directories
mkdir input
mkdir output
mkdir scenarios

# Copies the Cloud Trace Generator JAR
cp ../out/artifacts/cloud_workload_trace_generator_jar/cloud-workload-trace-generator.jar .
cp ../input/instanceTypes.csv ./input/
