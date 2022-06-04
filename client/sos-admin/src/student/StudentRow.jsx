import axios from "axios";
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
  let handleSubmit = (event) => {
    event.preventDefault();
    axios
    .post("http://localhost:8080/api/delete/student", {
      student_id: student.student_id,
    })
    .then((res) => {
      window.location = "/students";
    });
  };

  return (
    <tr>
      <td>{student.student_id}</td>
      <td>{getFullname(student.fullname)}</td>
      <td>{student.gender}</td>
      <td>{getDateofbirth(student.date_of_birth)}</td>
      <td>{getAddress(student.address)}</td>
      <td>{student.emails}</td>
      <td>{student.phones}</td>
      <td>{student.about}</td>
      <td>
        <form onSubmit={handleSubmit}>
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
