# 🚨 Operations Runbook

## Quick Reference
- **App**: Meli Item Detail API  
- **Port**: 8080
- **Tech**: Spring Boot 3.2.0 + Java 17
- **Architecture**: Hexagonal + Multi-module Maven

## Health Checks
```bash
# Application health
curl http://localhost:8080/actuator/health

# Check running processes
ps aux | grep meli-item-detail
```

## Common Issues

### Application Won't Start
**Symptoms**: Port 8080 occupied, startup errors
```bash
# Kill process on port 8080
sudo lsof -ti:8080 | xargs kill -9

# Start application
mvn spring-boot:run -pl rest-adapter
```

### Memory Issues (OutOfMemoryError)
**Symptoms**: JVM crashes, slow response times
```bash
# Check memory usage
jstat -gc [PID]

# Increase heap size
export JAVA_OPTS="-Xmx2048m -Xms1024m"
mvn spring-boot:run -pl rest-adapter
```

### High Response Times
**Symptoms**: Timeouts, slow API responses
```bash
# Check logs for performance issues
tail -f rest-adapter/logs/meli-item-detail-api.log | grep "SLOW"

# Monitor thread dumps
jstack [PID] > thread_dump.txt
```

## Deployment Commands
```bash
# Full build and test
mvn clean install

# Run specific module
mvn spring-boot:run -pl rest-adapter

# Run with profile
mvn spring-boot:run -pl rest-adapter -Dspring.profiles.active=prod

# Run in background
nohup mvn spring-boot:run -pl rest-adapter &
```

## Log Locations
- **Application**: `rest-adapter/logs/meli-item-detail-api.log`
- **Archive**: `rest-adapter/logs/meli-item-detail-api.2025-07-20.gz`
- **Root**: `logs/meli-item-detail-api.log`

## Monitoring
```bash
# Check application status
curl -s http://localhost:8080/actuator/health | jq '.'

# Test main endpoint
curl -s http://localhost:8080/products/MLB123456789

# Monitor logs in real-time
tail -f rest-adapter/logs/meli-item-detail-api.log
```

## Emergency Contacts
- **Tech Lead**: thiago@mercadolibre.com
- **DevOps**: devops-oncall@mercadolibre.com  
- **SRE**: thiago@mercadolibre.com
