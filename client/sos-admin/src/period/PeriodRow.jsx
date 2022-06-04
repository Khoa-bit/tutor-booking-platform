import axios from "axios";
import React from "react";

function PeriodRow({ period }) {
  let handleSubmit = (event) => {
    event.preventDefault();
    axios
    .post("http://localhost:8080/api/delete/period", {
      period_id: period.period_id,
    })
    .then((res) => {
      window.location = "/periods";
    });
  };

  return (
    <tr>
      <td>{period.period_id}</td>
      <td>{period.tutor_id}</td>
      <td>{period.student_id}</td>
      <td>{period.start_time}</td>
      <td>{period.end_time}</td>
      <td>{period.day}</td>
      <td>
        <form onSubmit={handleSubmit}>
          <input hidden type="text" name="period_id" defaultValue={period.period_id} />
          <button type="submit" className="btn btn-danger">
            Delete
          </button>
        </form>
      </td>
    </tr>
  );
}

export default PeriodRow;
