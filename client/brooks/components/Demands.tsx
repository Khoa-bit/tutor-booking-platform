import React from 'react';
import Image from 'next/image'
import avatar from '../img/avatar.png'

import CalendarIcon from "./Icons/CalendarIcon";
import ClockIcon from "./Icons/ClockIcon";
import EducationIcon from "./Icons/EducationIcon";
import MoneyIcon from "./Icons/MoneyIcon";
import PoiIcon from "./Icons/PoiIcon";

import EnglishIcon from "./Icons/EnglishIcon";
import ArtIcon from "./Icons/ArtIcon";
import BiologyIcon from "./Icons/BiologyIcon";
import ChemistryIcon from "./Icons/ChemistryIcon";
import GeographyIcon from "./Icons/GeographyIcon";
import HistoryIcon from "./Icons/HistoryIcon";
import ITIcon from "./Icons/ITIcon";
import LiteratureIcon from "./Icons/LiteratureIcon";
import MusicIcon from "./Icons/MusicIcon";
import MathsIcon from "./Icons/MathsIcon";
import PhysicsIcon from "./Icons/PhysicsIcon";
import VietnameseIcon from "./Icons/VietnameseIcon";

interface DemandProps {
    name: string;
    grade: number;
    days: number;
    time: number;
    salary: number;
    open: string;
    location: string;
    children?: React.ReactNode;
}

function Demand(props: DemandProps) {
    return (
        <div className="w-full p-6 space-y-4 border rounded-2xl border-gray-300 bg-white ">
            <div className="flex justify-center items-center">
                <div className="justify-center">
                    <div className="flex justify-center mb-2">{props.children}</div>
                    <p className="text-center font-bold text-base mb-1">{props.name}</p>
                    <p className="font-normal text-sm">For student grade {props.grade}</p>
                </div>
            </div>

            <div className="">
                <div className="flex items-center gap-2 mb-2">
                    <CalendarIcon/>
                    <p className="font-semibold text-xs text-gray-700"> {props.days} days per week</p>
                </div>

                <div className="flex items-center gap-2 mb-2">
                    <ClockIcon/>
                    <p className="font-semibold text-xs text-gray-700"> {props.time} mins per day </p>
                </div>

                <div className="flex items-center gap-2 mb-2">
                    <MoneyIcon/>
                    <p className="font-semibold text-xs text-gray-700"> {props.salary} đ </p>
                </div>

                <div className="flex items-center gap-2 mb-2">
                    <EducationIcon/>
                    <p className="font-semibold text-xs text-gray-700"> Open for {props.open} </p>
                </div>

                <div className="flex items-center gap-2 mb-2">
                    <PoiIcon/>
                    <p className="font-semibold text-xs text-gray-700"> {props.location} </p>
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

export default function Demands() {
    return (
        <div className=" py-6 mx-40">
            <h1 className="mb-6 text-2xl font-bold">All Demands</h1>
            <div className="gap-8 mb-6 flex justify-start place-items-center">
                <Demand name="English" open={'University students'} grade={8} days={3} time={90} location={'Binh Thanh, D.1'} salary={8000000}><EnglishIcon/></Demand>
                <Demand name="Mathematics" open={'Teachers'} grade={12} days={2} time={90} location={'Binh Thanh, Go Vap'} salary={400000}><MathsIcon/></Demand>
                <Demand name="Chemistry" open={'Teachers'} grade={8} days={2} time={90} location={'D.4, Tan Binh'} salary={700000}><ChemistryIcon/></Demand>
                <Demand name="Literature" open={'Teachers'} grade={9} days={2} time={90} location={'D.12, Thu Duc'} salary={500000}><LiteratureIcon/></Demand>
            </div>
            <button type="button"
                        className="h-11 w-fit inline-flex items-center justify-center rounded-md border border-gray-300 bg-white px-4 py-2 text-sm font-bold text-primary-default hover:bg-gray-300 focus:outline-none focus-visible:ring-primary-default "
                        >Show more</button>
        </div>
    );
}