import { ErrorMessage } from "@hookform/error-message";
import authService from "@lib/service/authService";
import type { NextPage } from "next";
import Head from "next/head";
import { useRouter } from "next/router";
import { useState } from "react";
import { SubmitHandler, useForm } from "react-hook-form";
import EyeIcon from "@components/Icons/EyeIcon";
import { CogIcon } from "@heroicons/react/solid";
import Link from "next/link";
import AuthLayout from "@components/authLayout";

type Inputs = {
  username: string;
  password: string;
};

const Login: NextPage = () => {
  const router = useRouter();
  const [formError, setFormError] = useState(<></>);
  const [passwordVisibility, setPasswordVisibility] = useState(false);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<Inputs>();

  const onSubmit: SubmitHandler<Inputs> = async (data) => {
    setIsSubmitting(true);
    setFormError(<></>);
    const result = await authService.login(data.username, data.password);
    if ("error" in result) {
      setFormError(
        <p className="mb-4 w-11/12 rounded-lg bg-rose-400 p-2 font-bold text-white">
          {result.message}
        </p>
      );
    } else {
      router.push("/");
      return;
    }
    setIsSubmitting(false);
  };

  return (
    <>
      <Head>
        <title>Login</title>
        <meta name="description" content="Login to HomeTutor" />
        <link rel="icon" href="/static/favicon.ico" />
      </Head>
      <AuthLayout>
        <form
          className="flex w-full flex-col items-center py-3"
          onSubmit={handleSubmit(onSubmit)}
        >
          {formError}
          <ErrorMessage
            errors={errors}
            name="username"
            render={({ message }) => (
              <p className="mb-1 w-11/12 text-right text-rose-500">{message}</p>
            )}
          />
          <input
            className={
              "mb-4 w-11/12 rounded-lg border-0 bg-gray-100 focus:ring-2 focus:ring-blue-200 focus:ring-offset-2 "
            }
            type="text"
            placeholder="Username"
            {...register("username", { required: "Username is required" })}
          />
          <ErrorMessage
            errors={errors}
            name="password"
            render={({ message }) => (
              <p className="mb-1 w-11/12 text-right text-rose-500">{message}</p>
            )}
          />
          <div className="relative mb-1 w-11/12">
            <input
              className={
                "w-full rounded-lg border-0 bg-gray-100 pr-9 focus:ring-2 focus:ring-blue-200 focus:ring-offset-2 "
              }
              type={passwordVisibility ? "text" : "password"}
              placeholder="Password"
              {...register("password", { required: "Password is required" })}
            />
            <EyeIcon
              className="absolute top-1/2 right-2 w-6 -translate-y-1/2"
              onClick={() => setPasswordVisibility(!passwordVisibility)}
            ></EyeIcon>
          </div>
          <Link href="#">
            <a className="mb-4 w-11/12 text-right font-bold hover:underline">
              Forgot password?
            </a>
          </Link>

          <button
            className={
              "inline-flex  items-center rounded-lg bg-blue-500 p-2 font-semibold text-white transition-colors hover:bg-blue-400 focus:ring-2 focus:ring-blue-200 focus:ring-offset-2 " +
              (isSubmitting ? "bg-blue-400" : "")
            }
            type="submit"
            disabled={isSubmitting}
          >
            {isSubmitting && (
              <CogIcon className="mr-1 h-5 w-5 animate-spin"></CogIcon>
            )}
            Log in
          </button>
        </form>
      </AuthLayout>
    </>
  );
};

export default Login;
