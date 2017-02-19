from flask import Flask, request, jsonify
import requests
import json

app = Flask(__name__)

@app.route('/medicine', methods=['GET', 'POST'])
def medicine():
    return jsonify({"text":"Hello World!"})


if __name__ == '__main__':
    print('')
    app.run(host='0.0.0.0', port=8001)
