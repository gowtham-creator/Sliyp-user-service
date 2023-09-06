FROM adoptopenjdk/openjdk11:alpine-jre



VOLUME /tmp
ADD target/user-service-0.0.1-SNAPSHOT.jar app.jar

# Twistlock Container Defender - app embedded
ADD twistlock_defender_app_embedded.tar.gz /tmp
ENV DEFENDER_TYPE="appEmbedded"
ENV DEFENDER_APP_ID="oms"
ENV WS_ADDRESS="wss://asia-southeast1.cloud.twistlock.com:443"
ENV DATA_FOLDER="/tmp"
ENV INSTALL_BUNDLE="eyJzZWNyZXRzIjp7InNlcnZpY2UtcGFyYW1ldGVyIjoidXg5Umppb0pvSW02bHBuTm03N2Q3T0RXUlUrcHcyL0F0WFNxaUJVUHkwOFZ5QXVVdE5HWnlZWE8rSHdOL0tIeU9LU3VmSjhidjIyZ0Y0NVpDOXc2a0E9PSJ9LCJnbG9iYWxQcm94eU9wdCI6eyJodHRwUHJveHkiOiIiLCJub1Byb3h5IjoiIiwiY2EiOiIiLCJ1c2VyIjoiIiwicGFzc3dvcmQiOnsiZW5jcnlwdGVkIjoiIn19LCJjdXN0b21lcklEIjoiYXdzLXNpbmdhcG9yZS05NjExNDQwNjIiLCJhcGlLZXkiOiJnTEFOMlhnNUc0SjUvYUh6bHI3N0h1Wk5iaHI0UHR4aHNwRDdJQmlIS0kvSHdZR0J2bTA5bS82VW9NaE91cWN0T0pXK3BFTzdlbUJuVU5peEJ6OHF5Zz09IiwibWljcm9zZWdDb21wYXRpYmxlIjpmYWxzZX0="
ENTRYPOINT ["/tmp/defender", "app-embedded", "java", "-Dspring.profiles.active=dev", "-jar","/app.jar"]

