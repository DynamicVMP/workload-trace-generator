#!/bin/bash

OPEN_BRACKET='{'
CLOSE_BRACKET="}"

# Static part of the configuration input file
STATIC_CONFIG='"scenarioStartTime":1,"scenarioEndTime":100,"numberOfServices":100,"revenueCPU":"0.01F","revenueRAM":"0.002F","revenueNET":"0.0004F","instanceTypesFileLocation":"input/instanceTypes.csv",'

# Output file path configuration
START_OUTPUT_FILENAME='"outputFileLocation":"'
END_OUTPUT_FILENAME='",'

# Uniform parameters
HORZ_UNI='"horizontalElasticityConfiguration":{"distributionType":"UNIFORM","floor":1,"ceiling":11},'
VERT_UNI='"verticalElasticityConfiguration":{"distributionType":"UNIFORM","floor":1,"ceiling":11},'
SERV_UNI='"serverOverbookingConfiguration":{"distributionType":"UNIFORM","floor":1,"ceiling":100},'
NET_UNI='"networkOverbookingConfiguration":{"distributionType":"UNIFORM","floor":1,"ceiling":100}'

# Poisson parameters
HORZ_POS='"horizontalElasticityConfiguration":{"distributionType":"POISSON","lambda":"7F"},'
VERT_POS='"verticalElasticityConfiguration":{"distributionType":"POISSON","lambda":"5F"},'
SERV_POS='"serverOverbookingConfiguration":{"distributionType":"POISSON","lambda":"70F"},'
NET_POS='"networkOverbookingConfiguration":{"distributionType":"POISSON","lambda":"70F"}'

DIST_NAMES=('u' 'p')
ELEMENTS=${#DIST_NAMES[@]}
FILE_NAME="test.txt"

print_file () {
  # Order of parameters
  # horz, vert, serv, net, inputFileLocation, outputFileLocation
  # echo $1
  # echo $2
  # echo $3
  # echo $4
  # echo $5
  # echo $6
  echo "" > $5
  echo $OPEN_BRACKET >> $5
  echo $STATIC_CONFIG >> $5
  COMPLETE_OUTPUT_PATH=$START_OUTPUT_FILENAME$6$END_OUTPUT_FILENAME
  echo $COMPLETE_OUTPUT_PATH >> $5
  echo $1 >> $5
  echo $2 >> $5
  echo $3 >> $5
  echo $4 >> $5
  echo $CLOSE_BRACKET >> $5
}

for (( i=0;i<$ELEMENTS;i++ )); do
  HORZ_ELAST=''
  case ${DIST_NAMES[i]} in
    'u')  HORZ_ELAST=$HORZ_UNI;;
    'p')  HORZ_ELAST=$HORZ_POS;;
  esac
  for (( j=0;j<$ELEMENTS;j++ )); do
    VERT_ELAST=''
    case ${DIST_NAMES[j]} in
      'u')  VERT_ELAST=$VERT_UNI;;
      'p')  VERT_ELAST=$VERT_POS;;
    esac
    for (( k=0;k<$ELEMENTS;k++ )); do
      SERV_OVER=''
      case ${DIST_NAMES[k]} in
        'u')  SERV_OVER=$SERV_UNI;;
        'p')  SERV_OVER=$SERV_POS;;
      esac
      for (( l=0;l<$ELEMENTS;l++ )); do
        NET_OVER=''
        case ${DIST_NAMES[l]} in
          'u')  NET_OVER=$NET_UNI;;
          'p')  NET_OVER=$NET_POS;;
        esac
        OUTPUTS_PER_SCENARIO=5
        for (( m=0;m<$OUTPUTS_PER_SCENARIO;m++ )); do
          OUTPUT_FILE_NAME="output/"${DIST_NAMES[i]}${DIST_NAMES[j]}${DIST_NAMES[k]}${DIST_NAMES[l]}"-0"$m".csv"
          FILE_NAME="input/configInput-"${DIST_NAMES[i]}${DIST_NAMES[j]}${DIST_NAMES[k]}${DIST_NAMES[l]}".json"

          print_file $HORZ_ELAST $VERT_ELAST $SERV_OVER $NET_OVER $FILE_NAME $OUTPUT_FILE_NAME

          echo "Running Clour Trace Generator for input: "$FILE_NAME
          echo "Output stored in: "$OUTPUT_FILE_NAME

          java -jar cloud-workload-trace-generator.jar $FILE_NAME
        done
      done
    done
  done
done
