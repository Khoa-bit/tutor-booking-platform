import React from "react";

function PeriodRow({ period }) {
  return (
    <tr>
      <td>{period.period_id}</td>
      <td>{period.start_time}</td>
      <td>{period.end_time}</td>
      <td>{period.day}</td>
      <td>
        <form action="">
          <input hidden type="text" name="period_id" defaultValue={period.period_id} />
          <button type="submit" className="btn btn-primary">
            Detail
          </button>
        </form>
      </td>
      <td>
        <form action="">
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
