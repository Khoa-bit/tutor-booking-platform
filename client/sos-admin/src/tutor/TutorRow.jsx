import React from "react";

function getFullname(fullname) {
  return fullname.last_name + " " + fullname.first_name;
}

function getDateofbirth(date_of_birth) {
  return date_of_birth.day + "/" + date_of_birth.month + "/" + date_of_birth.year;
}

function getAddress(address) {
  return address.province_city + ", " + address.ward_district + ", " + address.home_number;
}

function TutorRow({ tutor }) {
  return (
    <tr>
      <td>{tutor.tutor_id}</td>
      <td>{getFullname(tutor.fullname)}</td>
      <td>{getDateofbirth(tutor.date_of_birth)}</td>
      <td>{getAddress(tutor.address)}</td>
      <td>
        <form action="">
          <input hidden type="text" name="tutor_id" defaultValue={tutor.tutor_id} />
          <button type="submit" className="btn btn-primary">
            Detail
          </button>
        </form>
      </td>
      <td>
        <form action="">
          <input hidden type="text" name="tutor_id" defaultValue={tutor.tutor_id} />
          <button type="submit" className="btn btn-danger">
            Delete
          </button>
        </form>
      </td>
    </tr>
  );
}

export default TutorRow;
