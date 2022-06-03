import React from "react";


function StudentAuthenticationRow({ studentAuthentication }) {
  return (
    <tr>
      <td>{studentAuthentication.student_id}</td>
      <td>{studentAuthentication.username}</td>
      <td>{studentAuthentication.password}</td>
      <td>
        <form action="">
          <input hidden type="text" name="student_id" defaultValue={studentAuthentication.Student_id} />
          <button type="submit" className="btn btn-danger">
            Delete
          </button>
        </form>
      </td>
    </tr>
  );
}

export default StudentAuthenticationRow;
