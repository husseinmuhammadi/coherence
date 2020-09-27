ARG HOST_NAME
FROM maven:3.6.0-jdk-11 AS build
RUN mkdir -p /root/.m2 \
    && mkdir /root/.m2/repository
COPY maven/conf/settings.xml /root/.m2
WORKDIR /workspace
COPY pom.xml pom.xml
COPY cache-demo cache-demo
COPY cache-node cache-node
RUN mvn clean package

FROM openjdk:11
WORKDIR /app
COPY --from=build /workspace/cache-demo/target/coherence-cache-demo.jar .
COPY --from=build /workspace/cache-demo/target/lib ./lib
EXPOSE 9010
ENTRYPOINT ["java","-Xdebug","-Xrunjdwp:server=y,transport=dt_socket,address=4000,suspend=n", \
    "-Dcom.sun.management.jmxremote", \
    "-Dcom.sun.management.jmxremote.port=9010", \
    "-Dcom.sun.management.jmxremote.local.only=false", \
    "-Dcom.sun.management.jmxremote.authenticate=false", \
    "-Dcom.sun.management.jmxremote.ssl=false", \
    "-Dcom.sun.management.jmxremote.rmi.port=9010", \
    "-Djava.rmi.server.hostname=0.0.0.0", \
    "-jar","coherence-cache-demo.jar"]

# ENTRYPOINT ["java","-Xdebug","-Xrunjdwp:server=y,transport=dt_socket,address=4000,suspend=n", \
#     "-Dcom.sun.management.jmxremote", \
#     "-Dcom.sun.management.jmxremote.port=9010", \
#     "-Dcom.sun.management.jmxremote.local.only=false", \
#     "-Dcom.sun.management.jmxremote.authenticate=false", \
#     "-Dcom.sun.management.jmxremote.ssl=false", \
#     "-Dcom.sun.management.jmxremote.rmi.port=9010", \
#     "-Djava.rmi.server.hostname=$HOST_NAME", \    
#     "-jar","coherence-cache-demo.jar"]

# docker build -t coherence-cache-demo:1.0.12 .
# docker run -d -p 9010:9010 -e HOST_NAME=TAN-HEJRI-HP coherence-cache-demo:1.0.12
# docker run --rm --name node1 -e HOST_NAME=TAN-HEJRI-HP -it cms-coherence-cache:1.0.0.6