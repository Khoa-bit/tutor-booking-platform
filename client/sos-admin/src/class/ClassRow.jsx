import React from "react";

function getAddress(address) {
  return address.province_city + ", " + address.ward_district + ", " + address.home_number;
}

function ClassRow({ class1 }) {
  return (
    <tr>
      <td>{class1.class_id}</td>
      <td>{class1.tutor_id}</td>
      <td>{class1.student_id}</td>
      <td>{class1.grade}</td>
      <td>{class1.subjects}</td>
      <td>{getAddress(class1.address)}</td>
      <td>{class1.salary}</td>
      <td>{class1.requirement}</td>

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
