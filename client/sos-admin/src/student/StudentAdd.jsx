import React from "react";
import axios from "axios";

class StudentAdd extends React.Component {
  state = {
    student_id: "",
    first_name: "",
    last_name: "",
    gender: "",
    day: "",
    month: "",
    year: "",
    province_city: "",
    ward_district: "",
    home_number: "",
    parent_first_name: "",
    parent_last_name: "",
    email: "",
    phone: "",
    about: "",
  };

  handleChange_student_id = (event) => {
    this.setState({ student_id: event.target.value });
    console.log(this.state);
  };

  handleChange_first_name = (event) => {
    this.setState({ first_name: event.target.value });
    console.log(this.state);
  };

  handleChange_last_name = (event) => {
    this.setState({ last_name: event.target.value });
  };

  handleChange_gender = (event) => {
    this.setState({ gender: event.target.value });
  };

  handleChange_day = (event) => {
    this.setState({ day: event.target.value });
  };

  handleChange_month = (event) => {
    this.setState({ month: event.target.value });
  };

  handleChange_year = (event) => {
    this.setState({ year: event.target.value });
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

  handleChange_parent_first_name = (event) => {
    this.setState({ parent_first_name: event.target.value });
    console.log(this.state);
  };

  handleChange_parent_last_name = (event) => {
    this.setState({ parent_last_name: event.target.value });
  };

  handleChange_email = (event) => {
    this.setState({ email: event.target.value });
  };

  handleChange_phone = (event) => {
    this.setState({ phone: event.target.value });
  };

  handleChange_about = (event) => {
    this.setState({ about: event.target.value });
  };

  componentDidMount() {}

  handleSubmit = (event) => {
    event.preventDefault();

    axios
      .post("http://localhost:8080/api/add/student", {
        first_name: this.state.first_name,
        last_name: this.state.last_name,
        gender: this.state.gender,
        day: this.state.day,
        month: this.state.month,
        year: this.state.year,
        province_city: this.state.province_city,
        ward_district: this.state.ward_district,
        home_number: this.state.home_number,
        parent_first_name: this.state.parent_first_name,
        parent_last_name: this.state.parent_last_name,
        email: this.state.email,
        phone: this.state.phone,
        about: this.state.about,
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
          <h1 className="text-center">Student Profile</h1>

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

                  <div className="mb-3 d-flex justify-content-between align-items-center">
                    <div className="">
                      <label className="form-label">Parent First name</label>
                      <input
                        type="text"
                        onChange={this.handleChange_parent_first_name}
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                    <div className="">
                      <label className="form-label">Parent Last name</label>
                      <input
                        type="text"
                        onChange={this.handleChange_parent_last_name}
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input
                      type="email"
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
                      Add
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

export default StudentAdd;
