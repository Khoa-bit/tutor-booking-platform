import React from "react";
import axios from "axios";
import { Link } from "react-router-dom";

class TutorAdd extends React.Component {
  state = {
    tutor_id: "",
    first_name: "",
    last_name: "",
    gender: "",
    day: "",
    month: "",
    year: "",
    province_city: "",
    ward_district: "",
    home_number: "",
    email: "",
    phone: "",
    job: "",
    graduated_school: "",
    major: "",
    qualification: "",
    graduated_year: "",
    grades: "",
    subjects: "",
    minimum_salary_requirement: "",
    about: "",
  };

  handleChange_tutor_id = (event) => {
    this.setState({ tutor_id: event.target.value });
    console.log(this.state);
  }

  handleChange_first_name = (event) => {
    this.setState({ first_name: event.target.value });
    console.log(this.state);
  }

  handleChange_last_name = (event) => {
    this.setState({ last_name: event.target.value });
  }

  handleChange_gender = (event) => {
    this.setState({ gender: event.target.value });
  }

  handleChange_day = (event) => {
    this.setState({ day: event.target.value });
  }

  handleChange_month = (event) => {
    this.setState({ month: event.target.value });
  }

  handleChange_year = (event) => {
    this.setState({ year: event.target.value });
  }

  handleChange_province_city = (event) => {
    this.setState({ province_city: event.target.value });
  }

  handleChange_ward_district = (event) => {
    this.setState({ ward_district: event.target.value });
  }

  handleChange_home_number = (event) => {
    this.setState({ home_number: event.target.value });
  }

  handleChange_email = (event) => {
    this.setState({ email: event.target.value });
  }

  handleChange_phone = (event) => {
    this.setState({ phone: event.target.value });
  }

  handleChange_job = (event) => {
    this.setState({ job: event.target.value });
  }

  handleChange_graduated_school = (event) => {
    this.setState({ graduated_school: event.target.value });
  }

  handleChange_major = (event) => {
    this.setState({ major: event.target.value });
  }

  handleChange_qualification = (event) => {
    this.setState({ qualification: event.target.value });
  }

  handleChange_graduated_year = (event) => {
    this.setState({ graduated_year: event.target.value });
  }

  handleChange_grades = (event) => {
    this.setState({ grades: event.target.value });
  }

  handleChange_subjects = (event) => {
    this.setState({ subjects: event.target.value });
  }

  handleChange_minimum_salary_requirement = (event) => {
    this.setState({ minimum_salary_requirement: event.target.value });
  }

  handleChange_about = (event) => {
    this.setState({ about: event.target.value });
  }

  componentDidMount() {}

  handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/api/add/tutor", {
        first_name: this.state.first_name,
        last_name: this.state.last_name,
        gender: this.state.gender,
        day: this.state.day,
        month: this.state.month,
        year: this.state.year,
        province_city: this.state.province_city,
        ward_district: this.state.ward_district,
        home_number: this.state.home_number,
        email: this.state.email,
        phone: this.state.phone,
        job: this.state.job,
        graduated_school: this.state.graduated_school,
        major: this.state.major,
        qualification: this.state.qualification,
        graduated_year: this.state.graduated_year,
        grades: this.state.grades,
        subjects: this.state.subjects,
        minimum_salary_requirement: this.state.minimum_salary_requirement,
        about: this.state.about,
      })
      .then((res) => {
        /*const tutors = res.data;
        this.setState({ tutors });*/
        window.location = "/tutors";
      });
  };

  render() {
    return (
      <div className="container mt-5 mb-5">
        <header>
          <h1 className="text-center">Add a Tutor</h1>

          <div className="d-flex justify-content-center">
            <div className="card mt-5 w-50">
              <div className="card-body">
                <form onSubmit={this.handleSubmit}>
                  <div className="mb-3 d-flex justify-content-between align-items-center">
                    <div className="">
                      <label className="form-label">First name</label>
                      <input
                        type="text"
                        onChange={this.handleChange_first_name}
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                    <div className="">
                      <label className="form-label">Last name</label>
                      <input
                        type="text"
                        onChange={this.handleChange_last_name}
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Gender</label>
                    <input
                      type="text"
                      onChange={this.handleChange_gender}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div>Date of birth</div>

                  <div className="mb-3 d-flex justify-content-between align-items-center">
                    <div className="">
                      <label className="form-label">Day</label>
                      <input
                        type="text"
                        onChange={this.handleChange_day}
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                    <div className="ms-3">
                      <label className="form-label">Month</label>
                      <input
                        type="text"
                        onChange={this.handleChange_month}
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                    <div className="ms-3">
                      <label className="form-label">Year</label>
                      <input
                        type="text"
                        onChange={this.handleChange_year}
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                  </div>

                  <div>Address</div>

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

                  <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input
                      type="text"
                      onChange={this.handleChange_email}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Phone</label>
                    <input
                      type="text"
                      onChange={this.handleChange_phone}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Job</label>
                    <input
                      type="text"
                      onChange={this.handleChange_job}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Graduated School</label>
                    <input
                      type="text"
                      onChange={this.handleChange_graduated_school}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Major</label>
                    <input
                      type="text"
                      onChange={this.handleChange_major}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Qualification</label>
                    <input
                      type="text"
                      onChange={this.handleChange_qualification}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Graduated Year</label>
                    <input
                      type="text"
                      onChange={this.handleChange_graduated_year}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  

                  <div className="mb-3">
                    <label className="form-label">Grade</label>
                    <input
                      type="text"
                      onChange={this.handleChange_grades}
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
                    <label className="form-label">
                      Minimum salary requirement
                    </label>
                    <input
                      type="text"
                      onChange={this.handleChange_minimum_salary_requirement}
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">About</label>
                    <textarea
                    onChange={this.handleChange_about}
                      className="form-control"
                      cols="30"
                      rows="5"
                    ></textarea>
                  </div>
                  <div className="d-flex justify-content-center">
                    <button type="submit" className="btn btn-success">
                      Create
                    </button>
                    <Link to="/tutors" className="btn btn-outline-primary ms-5">
                      Cancel
                    </Link>
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

export default TutorAdd;
