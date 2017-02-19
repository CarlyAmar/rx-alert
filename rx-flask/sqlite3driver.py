import sqlite3
import traceback

db_name="users.sqlite3"


def register_user(id):
    try:
        conn = sqlite3.connect(db_name)
        command="""INSERT INTO users (ID,MEDICINE) \
      VALUES ({0}, "FALSE");""".format(id)
        conn.execute(command)
        conn.commit()
        conn.close()
    except Exception as e:
        print("Database Error!")
        print(traceback.format_exc(e))

def user_needs_medicine(id):
    command="""SELECT * FROM users where ID='{0}'""".format(id)
    try:
        conn = sqlite3.connect(db_name)
        cursor = conn.execute(command)
        for row in cursor:
            if row[0] == "{0}".format(id):
                return row[1] == "TRUE" or row[1] == "True"
        conn.commit()
        conn.close()
    except Exception as e:
        print("Database Error!")
        print(traceback.format_exc(e))

def change_user_medication_status(id, status):
    command="""UPDATE users set MEDICINE = "{0}" where ID={1}""".format(status, id)
    try:
        conn = sqlite3.connect(db_name)
        cursor = conn.execute(command)
        conn.commit()
        conn.close()
        return status
    except Exception as e:
        print("Database Error!")
        print(traceback.format_exc(e))


if __name__ == "__main__":
    while True:
        exec(input("> "))
