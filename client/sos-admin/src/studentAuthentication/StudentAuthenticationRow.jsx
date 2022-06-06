import axios from "axios";
import React from "react";


function StudentAuthenticationRow({ studentAuthentication }) {
  let handleSubmit = (event) => {
    event.preventDefault();
    axios
    .post("https://tutor-booking-system.herokuapp.com/api/delete/student-authentication", {
      student_id: studentAuthentication.student_id,
    })
    .then((res) => {
      window.location = "/students-authentication";
    });
  };

  return (
    <tr>
      <td>{studentAuthentication.student_id}</td>
      <td>{studentAuthentication.username}</td>
      <td>{studentAuthentication.password}</td>
      <td>
        <form onSubmit={handleSubmit}>
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
