import axios from "axios";
import React from "react";


function TutorAuthenticationRow({ tutorAuthentication }) {
  let handleSubmit = (event) => {
    event.preventDefault();
    axios
    .post("http://localhost:8080/api/delete/tutor-authentication", {
      tutor_id: tutorAuthentication.tutor_id,
    })
    .then((res) => {
      window.location = "/tutors-authentication";
    });
  };

  return (
    <tr>
      <td>{tutorAuthentication.tutor_id}</td>
      <td>{tutorAuthentication.username}</td>
      <td>{tutorAuthentication.password}</td>
      <td>
        <form onSubmit={handleSubmit}>
          <input hidden type="text" name="tutor_id" defaultValue={tutorAuthentication.tutor_id} />
          <button type="submit" className="btn btn-danger">
            Delete
          </button>
        </form>
      </td>
    </tr>
  );
}

export default TutorAuthenticationRow;
