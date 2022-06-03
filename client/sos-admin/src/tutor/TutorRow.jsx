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
      <td>{tutor.gender}</td>
      <td>{getDateofbirth(tutor.date_of_birth)}</td>
      <td>{getAddress(tutor.address)}</td>
      <td>{tutor.emails}</td>
      <td>{tutor.phones}</td>
      <td>{tutor.job}</td>
      <td>{tutor.graduated_school}</td>
      <td>{tutor.major}</td>
      <td>{tutor.qualification}</td>
      <td>{tutor.graduated_year}</td>
      <td>{tutor.grades}</td>
      <td>{tutor.subjects}</td>
      <td>{tutor.minimum_salary_requirement}</td>
      <td>{tutor.about}</td>

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
