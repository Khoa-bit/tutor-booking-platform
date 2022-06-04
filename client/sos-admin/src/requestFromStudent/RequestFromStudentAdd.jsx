import React from "react";
import axios from "axios";

class RequestFromStudentAdd extends React.Component {
  state = {
    request_id: "",
    tutor_id: "",
    student_id: "",
    grade: "",
    subjects: "",
    province_city: "",
    ward_district: "",
    home_number: "",
    salary: "",
    requirement: "",
  };

  handleChange_request_id = (event) => {
    this.setState({ request_id: event.target.value });
    console.log(this.state);
  };

  handleChange_tutor_id = (event) => {
    this.setState({ tutor_id: event.target.value });
    console.log(this.state);
  };

  handleChange_student_id = (event) => {
    this.setState({ student_id: event.target.value });
    console.log(this.state);
  };

  handleChange_grade = (event) => {
    this.setState({ grade: event.target.value });
  };

  handleChange_subjects = (event) => {
    this.setState({ subejcts: event.target.value });
  };

  handleChange_province_city = (event) => {
    this.setState({ province_city: event.target.value });
  };

  handleChange_ward_district = (event) => {
    this.setState({ ward_district: event.target.value });
  };

  handleChange_home_number = (event) => {
    this.setState({ home_number: event.target.value });
  };

  handleChange_salary = (event) => {
    this.setState({ salary: event.target.value });
  };

  handleChange_requirement = (event) => {
    this.setState({ requirement: event.target.value });
  };

  componentDidMount() {}

  handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/api/add/request-from-student", {
        request_id: this.state.request_id,
        tutor_id: this.state.tutor_id,
        student_id: this.state.student_id,
        grade: this.state.grade,
        subjects: this.state.subjects,
        province_city: this.state.province_city,
        ward_district: this.state.ward_district,
        home_number: this.state.home_number,
        salary: this.state.salary,
        requirement: this.state.requirement,
      })
      .then((res) => {
        /*const tutors = res.data;
            this.setState({ tutors });*/
      });
  };

  render() {
    return (
      <div className="container mt-5 mb-5">
        <header>
          <h1 className="text-center">Class</h1>

          <div className="d-flex justify-content-center">
            <div className="card mt-5 w-50">
              <div className="card-body">
                <form onSubmit={this.handleSubmit}>
                  <div className="mb-3">
                    <label className="form-label">Tutor ID</label>
                    <input
                      type="text"
                      onChange={this.handleChange_tutor_id}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Student ID</label>
                    <input
                      type="text"
                      onChange={this.handleChange_student_id}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Grade</label>
                    <input
                      type="text"
                      onChange={this.handleChange_grade}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Subject</label>
                    <input
                      type="text"
                      onChange={this.handleChange_subjects}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Salary</label>
                    <input
                      type="text"
                      onChange={this.handleChange_salary}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Requirements</label>
                    <input
                      type="text"
                      onChange={this.handleChange_requirement}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3 d-flex justify-content-between align-items-center">
                    <div className="">
                      <label className="form-label">Province/City</label>
                      <input
                        type="text"
                        onChange={this.handleChange_province_city}
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                    <div className="ms-3">
                      <label className="form-label">Ward/District</label>
                      <input
                        type="text"
                        onChange={this.handleChange_ward_district}
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                    <div className="ms-3">
                      <label className="form-label">
                        Home Address/Road/...
                      </label>
                      <input
                        type="text"
                        onChange={this.handleChange_home_number}
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                  </div>

                  <div className="d-flex justify-content-center">
                    <button type="submit" className="btn btn-success">
                      Create
                    </button>
                    <button
                      type="submit"
                      className="btn btn-outline-primary ms-5"
                    >
                      Cancel
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </header>
      </div>
    );
  }
}

export default RequestFromStudentAdd;
