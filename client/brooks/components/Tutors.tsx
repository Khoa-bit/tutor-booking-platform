import React from 'react';
import Image from 'next/image'
import avatar from '../img/avatar.png'

import AccountIcon from "./Icons/AccountIcon";
import CompanyIcon from "./Icons/CompanyIcon";
import EducationIcon from "./Icons/EducationIcon";
import MoneyIcon from "./Icons/MoneyIcon";
import PoiIcon from "./Icons/PoiIcon";
import VerifyIcon from "./Icons/VerifyIcon";

interface TutorProps {
    name: string;
    position: string;
    subjects: string;
    open: string;
    age: number;
    degree: string;
    university: string;
    major: string;
    location: string;
    salary: number;
    children?: React.ReactNode;
}

function Tutor(props: TutorProps) {
    return (
        <div className="w-max p-6 space-y-4 border rounded-2xl border-gray-300 bg-white ">
            <div className="flex gap-x-2 items-center">
                <div className="w-16 h-16">
                    <Image className="" src={avatar} />
                </div>
                <div >
                    <p className="font-bold text-base">{props.name}</p>
                    <p className="font-semibold text-xs text-gray-700">{props.position}</p>
                </div>
            </div>

            <div>
                <div className="flex">
                    <p className="font-normal text-sm"><strong>Subjects: </strong>{props.subjects}</p>
                </div>
            </div>

            <div>
                <div className="flex">
                    <p className="font-normal text-sm"><strong>Open to: </strong>{props.open}</p>
                </div>
            </div>

            <div className="">
                <div className="flex items-center gap-2 mb-2">
                    <AccountIcon/>
                    <p className="font-semibold text-xs text-gray-700"> Age {props.age} </p>
                </div>

                <div className="flex items-center gap-2 mb-2">
                    <VerifyIcon/>
                    <p className="font-semibold text-xs text-gray-700"> {props.degree} Degree </p>
                </div>

                <div className="flex items-center gap-2 mb-2">
                    <CompanyIcon/>
                    <p className="font-semibold text-xs text-gray-700"> {props.university}  </p>
                </div>

                <div className="flex items-center gap-2 mb-2">
                    <EducationIcon/>
                    <p className="font-semibold text-xs text-gray-700"> Major in {props.major} </p>
                </div>

                <div className="flex items-center gap-2 mb-2">
                    <PoiIcon/>
                    <p className="font-semibold text-xs text-gray-700"> {props.location} </p>
                </div>

                <div className="flex items-center gap-2 mb-2">
                    <MoneyIcon/>
                    <p className="font-semibold text-xs text-gray-700"> Minimum of {props.salary}đ/section </p>
                </div>

            </div>
            <div className="flex justify-center">
                <button type="button"
                        className="h-10 w-24 inline-flex items-center justify-center rounded-md border border-transparent bg-primary-default px-4 py-2 text-sm font-medium text-white hover:bg-primary-dark focus:outline-none focus-visible:ring-primary-default "
                        >Detail</button>
            </div>
        </div>
    );
}

export default function Tutors() {
    return (
        <div className=" py-6 mx-40">
            <h1 className="mb-6 text-2xl font-bold">All Tutors</h1>
            <div className="gap-8 mb-6 flex justify-start place-items-center">
                <Tutor name="Khoa Nguyen" position={'Master of Education'} subjects={'Maths, Physics, Chemistry, English, Literature'} open={'grade 1 - 12, college preparation'} age={38} degree={'Master'} university={'University of Science'} major={'Math Education'} location={'Binh Thanh'} salary={200000}></Tutor>
                <Tutor name="Khoa Nguyen" position={'Master of Education'} subjects={'Maths, Physics, Chemistry, English, Literature'} open={'grade 1 - 12, college preparation'} age={38} degree={'Master'} university={'University of Science'} major={'Math Education'} location={'Binh Thanh'} salary={200000}></Tutor>
                <Tutor name="Khoa Nguyen" position={'Master of Education'} subjects={'Maths, Physics, Chemistry, English, Literature'} open={'grade 1 - 12, college preparation'} age={38} degree={'Master'} university={'University of Science'} major={'Math Education'} location={'Binh Thanh'} salary={200000}></Tutor>
                <Tutor name="Khoa Nguyen" position={'Master of Education'} subjects={'Maths, Physics, Chemistry, English, Literature'} open={'grade 1 - 12, college preparation'} age={38} degree={'Master'} university={'University of Science'} major={'Math Education'} location={'Binh Thanh'} salary={200000}></Tutor>
            </div>
            <button type="button"
                        className="h-11 w-fit inline-flex items-center justify-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-bold text-primary-default hover:bg-gray-300 focus:outline-none focus-visible:ring-primary-default "
                        >Show more</button>
        </div>
    );
}