"use client";

interface SidebarSectionProps {
    label: string;
    collapsed: boolean;
    children: React.ReactNode;
}

export function SidebarSection({ label, collapsed, children }: SidebarSectionProps) {
    return (
        <div className="mb-2">
            {!collapsed && (
                <p className="px-4 py-1.5 text-[10px] font-semibold text-white/25 uppercase tracking-widest select-none">
                    {label}
                </p>
            )}
            {collapsed && <div className="my-1 mx-3 border-t border-white/[0.06]" />}
            <div className="flex flex-col gap-0.5">{children}</div>
        </div>
    );
}