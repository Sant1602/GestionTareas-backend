"use client";

export default function Dashboard() {
    return (
        <div className="min-h-dvh w-full flex items-center justify-center bg-gradient-to-r from-[rgb(33,147,176)] to-[rgb(109,213,237)]">
            <div className="text-center px-6 text-white">
                <h1 className="text-3xl md:text-4xl font-semibold mb-3 drop-shadow-md">
                    Bienvenido de nuevo 
                </h1>
                <p className="text-white/90 text-sm md:text-base max-w-md mx-auto">
                    Continúa donde lo dejaste y haz que las cosas pasen hoy.
                </p>
                <div className="mt-6 h-1 w-20 mx-auto rounded-full bg-white/80" />
            </div>
        </div>
    );
}