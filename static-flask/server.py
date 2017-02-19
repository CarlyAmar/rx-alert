from flask import Flask, request
app = Flask(__name__, static_url_path='')

@app.route('/')
def root():
    return open("index.html").read()


if __name__ == '__main__':
    print('')
    app.run(host='0.0.0.0', debug=True, port=8002)
