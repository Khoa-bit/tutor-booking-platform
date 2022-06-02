import React from "react";

function RequestFromStudentRow({ requestFromStudent }) {
  return (
    <tr>
      <td>{requestFromStudent.request_id}</td>
      <td>{requestFromStudent.tutor_id}</td>
      <td>{requestFromStudent.student_id}</td>
      <td>
        <form action="">
          <input hidden type="text" name="request_id" defaultValue={requestFromStudent.request_id} />
          <button type="submit" className="btn btn-primary">
            Detail
          </button>
        </form>
      </td>
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
