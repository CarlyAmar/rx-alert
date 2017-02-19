from flask import Flask, request, jsonify
import requests
import json

from sqlite3driver import register_user, user_needs_medicine, change_user_medication_status

app = Flask(__name__)

@app.route('/medicine', methods=['GET', 'POST'])
def medicine():
    try:
        data = request.get_json()
        print(data)
    except:
        print("Cannot get json")
    if request.method == 'GET':
        print(data)
        need = user_needs_medicine(data["user_id"])
        if need == True:
            return jsonify({"medicine":"True"})
        else:
            return jsonify({"medicine":"False"})
    elif request.method == "POST":
        print(data)
        need = change_user_medication_status(data["user_id"], data["medicine"])
        uid="{0}".format(data["user_id"])
        medication="{0}".format(data["medicine"])
        return jsonify({"user_id":uid,"medicine":medication})
    return jsonify({"text":"Hello World"})


if __name__ == '__main__':
    print('')
    app.run(host='0.0.0.0', port=8001)
