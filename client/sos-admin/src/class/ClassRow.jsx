import React from "react";

function ClassRow({ class1 }) {
  return (
    <tr>
      <td>{class1.class_id}</td>
      <td>{class1.tutor_id}</td>
      <td>{class1.student_id}</td>
      <td>
        <form action="">
          <input hidden type="text" name="class_id" defaultValue={class1.class_id} />
          <button type="submit" className="btn btn-primary">
            Detail
          </button>
        </form>
      </td>
      <td>
        <form action="">
          <input hidden type="text" name="class_id" defaultValue={class1.class_id} />
          <button type="submit" className="btn btn-danger">
            Delete
          </button>
        </form>
      </td>
    </tr>
  );
}

export default ClassRow;
