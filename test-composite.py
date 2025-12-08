#!/usr/bin/env python3
"""
Script de test du service composite complet
Compatible Windows, macOS et Linux
"""
import subprocess
import sys
import time
import json

def run_curl(method, url, data=None):
    """Execute une requête curl"""
    cmd = ["curl", "-X", method, url]
    if data:
        cmd.extend(["-H", "Content-Type: application/json", "-d", json.dumps(data)])
    
    try:
        result = subprocess.run(cmd, capture_output=True, text=True)
        print(result.stdout)
        if result.stderr:
            print(result.stderr, file=sys.stderr)
    except FileNotFoundError:
        print("Error: curl not found. Please install curl.", file=sys.stderr)
        sys.exit(1)

def main():
    print("=" * 50)
    print("Test du service composite complet")
    print("=" * 50)
    print()
    
    # 1. Création d'un produit composite
    print("1. Creation d'un produit composite...")
    run_curl("POST", "http://localhost:7001/product-composite", {
        "product": {
            "productId": 2,
            "name": "MacBook Pro",
            "weight": 1800
        },
        "reviews": [{
            "productId": 2,
            "reviewId": 1,
            "author": "Jean",
            "subject": "Super",
            "content": "Excellent ordinateur"
        }],
        "recommendations": [{
            "productId": 2,
            "recommendationId": 1,
            "author": "Marie",
            "rate": 5,
            "content": "Je recommande fortement"
        }]
    })
    print("\n")
    time.sleep(3)
    
    # 2. Récupération du produit composite
    print("2. Recuperation du produit composite...")
    run_curl("GET", "http://localhost:7001/product-composite/2")
    print("\n")
    time.sleep(2)
    
    # 3. Suppression du produit composite
    print("3. Suppression du produit composite...")
    run_curl("DELETE", "http://localhost:7001/product-composite/2")
    print("\n")
    time.sleep(2)
    
    # 4. Vérification de la suppression
    print("4. Verification de la suppression (devrait retourner une erreur)...")
    run_curl("GET", "http://localhost:7001/product-composite/2")
    print("\n")
    
    print("=" * 50)
    print("Tests du composite termines!")
    print("=" * 50)

if __name__ == "__main__":
    main()
