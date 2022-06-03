import React from "react";
import axios from "axios";
import StudentRow from "./StudentRow";

class StudentTable extends React.Component {
  state = {
    students: [],
    student_id: "",
    fullname: "",
    province_city: "",
    ward_district: "",
  };

  renderTable(students) {
    this.setState({ students });
  }

  handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/api/filter-for-students", {
        student_id: this.state.student_id,
        fullname: this.state.fullname,
        province_city: this.state.province_city,
        ward_district: this.state.ward_district,
      })
      .then((res) => {
        const students = res.data;
        this.setState({ students });
      });
  };

  componentDidMount() {
    axios
      .get("http://localhost:8080/api/students")
      .then((res) => {
        const students = res.data;
        this.setState({ students });
      })
      .catch((error) => console.log(error));
  }

  handleChange_student_id = (event) => {
    this.setState({ student_id: event.target.value });
  };

  handleChange_fullname = (event) => {
    this.setState({ fullname: event.target.value });
  };

  handleChange_province_city = (event) => {
    this.setState({ province_city: event.target.value });
  };

  handleChange_ward_district = (event) => {
    this.setState({ ward_district: event.target.value });
  };

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <div className="row">
            <div className="col-2">
              <input
                className="form-control"
                id="student-id"
                name="student_id"
                onChange={this.handleChange_student_id}
                placeholder="Enter Student ID"
                type="text"
              />
            </div>
            <div className="col-3">
              <input
                className="form-control"
                id="student-fullname"
                name="student_fullname"
                onChange={this.handleChange_fullname}
                placeholder="Enter Student Fullname"
                type="text"
              />
            </div>
            <div className="col-3">
              <input
                className="form-control"
                id="province-city"
                list="province"
                name="province_city"
                onChange={this.handleChange_province_city}
                placeholder="Enter Province/City"
              />
              <datalist id="province">
                <option value="123" />
              </datalist>
            </div>
            <div className="col-3">
              <input
                className="form-control"
                id="ward-district"
                list="ward"
                name="ward_district"
                onChange={this.handleChange_ward_district}
                placeholder="Enter Ward/District"
              />
              <datalist id="ward">
                <option value="123" />
              </datalist>
            </div>
            <div className="col-1 d-flex justify-content-end">
              <button
                className="btn btn-primary"
                style={{ width: "100%" }}
                type="submit"
              >
                Search
              </button>
            </div>
          </div>
        </form>
        <div className="table-responsive border border-dark mt-3">
          <table className="table table-striped">
            <thead className="sticky-top">
              <tr className="bg-success">
                <th className="text-light">Student ID</th>
                <th className="text-light">Fullname</th>
                <th className="text-light">Gender</th>
                <th className="text-light">Date of birth</th>
                <th className="text-light">Address</th>
                <th className="text-light">Email</th>
                <th className="text-light">Phone</th>
                <th className="text-light">About</th>
                <th className="text-light"></th>
              </tr>
            </thead>
            <tbody>
              {/*{this.state.students.map((tutor) => (
            <TutorRow tutor={tutor} />
          ))} */}

              {Object.keys(this.state.students).map((key) => (
                <StudentRow key={key} student={this.state.students[key]} />
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default StudentTable;
