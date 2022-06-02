import React from "react";


function TutorAuthenticationRow({ tutorAuthentication }) {
  return (
    <tr>
      <td>{tutorAuthentication.tutor_id}</td>
      <td>{tutorAuthentication.username}</td>
      <td>{tutorAuthentication.password}</td>
      <td>
        <form action="">
          <input hidden type="text" name="tutor_id" defaultValue={tutorAuthentication.tutor_id} />
          <button type="submit" className="btn btn-primary">
            Detail
          </button>
        </form>
      </td>
      <td>
        <form action="">
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
