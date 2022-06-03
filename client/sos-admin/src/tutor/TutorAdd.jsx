import React from "react";
import axios from "axios";

class TutorAdd extends React.Component {
  state = {
    tutor_id: "",
    fullname: "",
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
    grades: [],
    subjects: [],
    minimum_salary_requirement: [],
    about: "",
  };

  componentDidMount() {
    
  }

  render() {
    return (
      <div className="container mt-5 mb-5">
        <header>
          <h1 className="text-center">Add a Tutor</h1>

          <div className="d-flex justify-content-center">
            <div className="card mt-5 w-50">
              <div className="card-body">
                <form>
                  <div className="mb-3 d-flex justify-content-between align-items-center">
                    <div className="">
                      <label className="form-label">First name</label>
                      <input
                        type="text"
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                    <div className="">
                      <label className="form-label">Last name</label>
                      <input
                        type="text"
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Gender</label>
                    <input
                      type="text"
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
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                    <div className="ms-3">
                      <label className="form-label">Month</label>
                      <input
                        type="text"
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                    <div className="ms-3">
                      <label className="form-label">Year</label>
                      <input
                        type="text"
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
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                    <div className="ms-3">
                      <label className="form-label">Ward/District</label>
                      <input
                        type="text"
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
                        className="form-control"
                        style={{ backgroundColor: "#f4f5f6" }}
                      />
                    </div>
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input
                      type="text"
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Phone</label>
                    <input
                      type="text"
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Job</label>
                    <input
                      type="text"
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Graduated School</label>
                    <input
                      type="text"
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Major</label>
                    <input
                      type="text"
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Qualification</label>
                    <input
                      type="text"
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Graduated Year</label>
                    <input
                      type="text"
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Grades</label>
                    <select name="grades" className="form-select" multiple>
                      <option value="Kindergarten">Kindergarten</option>
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                      <option value="4">4</option>
                      <option value="5">5</option>
                      <option value="6">6</option>
                      <option value="7">7</option>
                      <option value="8">8</option>
                      <option value="9">9</option>
                      <option value="10">10</option>
                      <option value="11">11</option>
                      <option value="12">12</option>
                      <option value="University Entrance Preparation">
                        University Entrance Preparation
                      </option>
                    </select>
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Majors</label>
                    <select name="grades" className="form-select" multiple>
                      <option value="Maths">Maths</option>
                      <option value="Physics">Physics</option>
                      <option value="Chemistry">Chemistry</option>
                      <option value="Biology">Biology</option>
                      <option value="Literature">Literature</option>
                      <option value="History">History</option>
                      <option value="Geography">Geography</option>
                      <option value="English">English</option>
                    </select>
                  </div>

                  <div className="mb-3">
                    <label className="form-label">
                      Minimum salary requirement
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      style={{ backgroundColor: "#f4f5f6" }}
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">About</label>
                    <textarea
                      className="form-control"
                      cols="30"
                      rows="5"
                    ></textarea>
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

export default TutorAdd;
