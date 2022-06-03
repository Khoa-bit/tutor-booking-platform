import React from "react";

function getAddress(address) {
  return address.province_city + ", " + address.ward_district + ", " + address.home_number;
}

function RequestFromStudentRow({ requestFromStudent }) {
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
        <form action="">
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
