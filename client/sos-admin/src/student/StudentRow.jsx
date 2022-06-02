import React from "react";

function getFullname(fullname) {
  return fullname.last_name + " " + fullname.first_name;
}

function getDateofbirth(date_of_birth) {
  return date_of_birth.day + "/" + date_of_birth.month + "/" + date_of_birth.year;
}

function getAddress(address) {
  return address.province_city + ", " + address.ward_district + ", " + address.home_number;
}

function StudentRow({ student }) {
  return (
    <tr>
      <td>{student.student_id}</td>
      <td>{getFullname(student.fullname)}</td>
      <td>{getDateofbirth(student.date_of_birth)}</td>
      <td>{getAddress(student.address)}</td>
      <td>
        <form action="">
          <input hidden type="text" name="student_id" defaultValue={student.student_id} />
          <button type="submit" className="btn btn-primary">
            Detail
          </button>
        </form>
      </td>
      <td>
        <form action="">
          <input hidden type="text" name="student_id" defaultValue={student.student_id} />
          <button type="submit" className="btn btn-danger">
            Delete
          </button>
        </form>
      </td>
    </tr>
  );
}

export default StudentRow;
