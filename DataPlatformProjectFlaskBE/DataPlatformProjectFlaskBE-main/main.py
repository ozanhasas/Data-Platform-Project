import pymongo
from flask import Flask, request, jsonify, make_response
from configparser import ConfigParser
import pandas as pd
from bson.objectid import ObjectId
import json
from flask_cors import CORS
import csv


app = Flask(__name__)
CORS(app)
config = ConfigParser()
config.read('app.cfg')
host = config['HOST']['host']
port = config['HOST']['port']
username = config['CREDENTIALS']['username']
password = config['CREDENTIALS']['password']
client_string = 'mongodb://'+username+':'+password+'@localhost:27017/tezdb?authSource=admin'
client = pymongo.MongoClient(client_string)
mydb = client["tezdb"]
dataset_collection = mydb["dataset"]


def find_delimiter(filename):
    sniffer = csv.Sniffer()
    with open(filename) as fp:
        delimiter = sniffer.sniff(fp.read(5000)).delimiter
    return delimiter


@app.route('/')
def home():
    return make_response(jsonify(Ad="DataPlatformProjectFlaskBE"), 200)

@app.route('/getdatasetview', methods=['POST'])
def getdatasetview():
    input_json = request.get_json()
    if input_json is None:
        return make_response(jsonify(Hata="Gerekli olan JSON dosyası gelmedi"), 400)
    else:
        dataset_id = input_json['datasetId']
        dataset_column_list = input_json["datasetColumnsList"]
        try:
            myquery = {"_id": ObjectId(dataset_id)}
            dataset_infos = dataset_collection.find(myquery)

            for x in dataset_infos:
                datapath = x['dataPathOfDataSet']

            seperator = find_delimiter(datapath)
            df = pd.read_csv(datapath, usecols=dataset_column_list, sep=seperator)
            df_head5 = df.head()
            output_json = df_head5.to_json(orient="records")
            parsed = json.loads(output_json)
            json.dumps(parsed)
            return make_response(jsonify(parsed), 200)
        except Exception as e:
            return make_response(jsonify(Hata=e.args), 400)


@app.route('/getdatasetcolumn', methods=['GET'])
def getdatasetcolumn():
    input_json = request.args
    if input_json is None:
        return make_response(jsonify(Hata="Gerekli olan param  gelmedi"), 400)
    else:
        dataset_id = input_json['datasetId']
        myquery = {"_id": ObjectId(dataset_id)}
        dataset_infos = dataset_collection.find(myquery)
        for x in dataset_infos:
            datapath = x['dataPathOfDataSet']
        if datapath is None:
            return make_response(jsonify(Hata="Aranan id ile ilgili bir dataset bulunamadı"), 400)
        else:
            df = pd.read_csv(datapath)
            columns  = list(df.columns)
            
            return make_response(jsonify(columns), 200)


@app.route('/getdatasetAsTxt',methods=['GET'])
def getdatasetAsTxt():
    input_json = request.args
    if input_json is None:
        return make_response(jsonify(Hata="Gerekli olan JSON dosyası gelmedi"), 400)
    else:
        dataset_id = input_json.get('datasetId')
        myquery = {"_id": ObjectId(dataset_id)}
        dataset_infos = dataset_collection.find(myquery)

        for x in dataset_infos:
            datapath = x['dataPathOfDataSet']
        seperator = find_delimiter(datapath)
        print(datapath)
        txt_datapath = datapath[:len(datapath)-3] + "txt"
        print(txt_datapath)
        with open(txt_datapath, "w", encoding="utf8") as my_output_file:
            with open(datapath, "r", encoding="utf8") as my_input_file:
                [my_output_file.write(" ".join(row) +'\n') for row in csv.reader(my_input_file, delimiter=seperator)]
            my_output_file.close()
        print("ends")
    return make_response(jsonify(txt_datapath),200)

@app.route('/getdatasetByColumnlist',methods=['GET'])
def getdatasetByColumnlist():
    input_json = request.args
    if input_json is None:
        return make_response(jsonify(Hata="Gerekli olan JSON dosyası gelmedi"), 400)
    else:
        dataset_id = input_json.get('datasetId')
        dataset_columnlist = list(input_json.get('datasetColumnsList').split(","))
        
        myquery = {"_id": ObjectId(dataset_id)}
        dataset_infos = dataset_collection.find(myquery)
        for x in dataset_infos:
            datapath = x['dataPathOfDataSet']

        seperator = find_delimiter(datapath)
        df = pd.read_csv(datapath, usecols=dataset_columnlist, delimiter=seperator)
        dowloaded_datapath = datapath[:3] + "downloaded_datasets" + datapath[21:len(datapath)-4] + "_downloaded.csv"
        df.to_csv(dowloaded_datapath, encoding="utf-8", index=False)

    return make_response(jsonify(str(dowloaded_datapath)),200)




@app.route('/getdatasetcolumn', methods=['GET'])
def getdatasetcolumn():
    input_json = request.args
    if input_json is None:
        return make_response(jsonify(Hata="Gerekli olan param  gelmedi"), 400)
    else:
        dataset_id = input_json['datasetId']
        myquery = {"_id": ObjectId(dataset_id)}
        dataset_infos = dataset_collection.find(myquery)
        for x in dataset_infos:
            datapath = x['dataPathOfDataSet']
        if datapath is None:
            return make_response(jsonify(Hata="Aranan id ile ilgili bir dataset bulunamadı"), 400)
        else:
            df = pd.read_csv(datapath)
            columns  = list(df.columns)
            
            return make_response(jsonify(columns), 200)

if __name__ == "__main__":
    app.run(host=host, port=int(port), debug=True)
