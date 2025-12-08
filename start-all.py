#!/usr/bin/env python3
"""
Script pour démarrer tous les services avec Docker Compose
Compatible Windows, macOS et Linux
"""
import subprocess
import sys

def main():
    print("=" * 50)
    print("Starting All Services with Docker...")
    print("=" * 50)
    
    try:
        # Démarrer les services avec Docker Compose
        subprocess.run(["docker", "compose", "up", "-d"], check=True)
        
        print()
        print("=" * 50)
        print("All services are starting!")
        print("=" * 50)
        print()
        print("Services:")
        print("- Gateway: http://localhost:8080")
        print("- Product Service: http://localhost:7002")
        print("- Recommendation Service: http://localhost:7003")
        print("- Review Service: http://localhost:7004")
        print("- Product Composite: http://localhost:7001")
        print()
        print("Infrastructure:")
        print("- MongoDB: localhost:27017")
        print("- MySQL: localhost:3306")
        print("- Kafka: localhost:9092")
        print("- Zookeeper: localhost:2181")
        print()
        print("Use 'docker compose logs -f' to see logs")
        print("Use 'python stop-all.py' to stop all services")
        print("=" * 50)
        
    except subprocess.CalledProcessError as e:
        print(f"Error: Failed to start services: {e}", file=sys.stderr)
        sys.exit(1)
    except FileNotFoundError:
        print("Error: Docker not found. Please install Docker.", file=sys.stderr)
        sys.exit(1)

if __name__ == "__main__":
    main()
