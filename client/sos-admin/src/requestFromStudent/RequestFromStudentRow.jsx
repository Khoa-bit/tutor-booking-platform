import axios from "axios";
import React from "react";

function getAddress(address) {
  return address.province_city + ", " + address.ward_district + ", " + address.home_number;
}

function RequestFromStudentRow({ requestFromStudent }) {
  let handleSubmit = (event) => {
    event.preventDefault();
    axios
    .post("http://localhost:8080/api/delete/request-from-student", {
      request_id: requestFromStudent.request_id,
    })
    .then((res) => {
      window.location = "/requests";
    });
  };

  return (
    <tr>
      <td>{requestFromStudent.request_id}</td>
      <td>{requestFromStudent.tutor_id}</td>
      <td>{requestFromStudent.student_id}</td>
      <td>{requestFromStudent.grade}</td>
      <td>{requestFromStudent.subjects}</td>
      <td>{getAddress(requestFromStudent.address)}</td>
      <td>{requestFromStudent.salary}</td>
      <td>{requestFromStudent.requirement}</td>
      <td>
        <form onSubmit={handleSubmit}>
          <input hidden type="text" name="request_id" defaultValue={requestFromStudent.request_id} />
          <button type="submit" className="btn btn-danger">
            Delete
          </button>
        </form>
      </td>
    </tr>
  );
}

export default RequestFromStudentRow;
