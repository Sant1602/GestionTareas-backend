import { cookies } from "next/headers";
import LoginForm from "@/components/pages/LoginPage";
import { redirect } from "next/navigation"

export default async function Home() {
  const cookieStore = await cookies()
  const token = cookieStore.get("token")?.value

  if(!token){
    return <LoginForm/>
  }
  redirect("/dashboard")
}
