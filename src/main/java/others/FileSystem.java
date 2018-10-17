package others;

import java.util.ArrayList;
import java.util.Scanner;

public class FileSystem {

    public static class DFTreeNode {

        private String nodeName;
        private ArrayList<DFTreeNode> childrenNodes;
        private boolean isRoot, isFile;
        private String permissions;
        private DFTreeNode parent;

        public DFTreeNode(String name, boolean isRoot, boolean isFile, String permission, DFTreeNode parentNode)
                throws Exception {
            // TODO Auto-generated constructor stub
            this.nodeName = name;
            if (isRoot && isFile) {
                Exception ex = new Exception("Invalid Operation!!! One entity can't be a root directory as well as a file.");
                throw ex;
            }
            this.isFile = isFile;
            this.isRoot = isRoot;
            if (isRoot)
                this.parent = null;
            else
                this.parent = parentNode;
            this.childrenNodes = new ArrayList<DFTreeNode>();
            this.permissions = permission;
        }

        public String getName() {
            return this.nodeName;
        }

        public boolean isRootFolder() {
            return this.isRoot;
        }

        public boolean isTypeFile() {
            return this.isFile;
        }

        public String getPermissions() {
            return this.permissions;
        }

        public DFTreeNode getParent() {
            return this.parent;
        }

        public void setPermissions(String newPermissions) {
            this.permissions = newPermissions;
        }

        public void setRoot() throws Exception {
            if (this.isFile) {
                Exception ex = new Exception("Invalid Operation!!! This is a file. You can't set root directory to a file.");
                throw ex;
            }
            this.isRoot = true;
            this.parent = null;
        }

        public void setTypeFile() throws Exception {
            if (this.childrenNodes != null) {
                Exception fileEx = new Exception("Invalid Operation!!! This is a directory and has children. You can't a file type to a directory.");
                throw fileEx;
            }
            this.isFile = true;
        }

        public void addChild(DFTreeNode tn) throws Exception {
            if (this.isFile) {
                Exception fileEx = new Exception("Invalid Operation!!! This is a file. You can't add child to a file type.");
                throw fileEx;
            }
            childrenNodes.add(tn);
        }

        public DFTreeNode getChild(String childName) throws Exception {
            if (!this.childrenNodes.isEmpty()) {
                for (DFTreeNode childNode : this.childrenNodes) {
                    if (childNode.getName().equals(childName))
                        return childNode;
                }
            }

            Exception childNotFoundEx = new Exception(childName + " is not present in this directory.");
            throw childNotFoundEx;
        }

        public void printChildren(boolean details) {
            if (details) {
                for (DFTreeNode cdf : childrenNodes) {
                    String end = "";
                    if (!cdf.isFile)
                        end = "/";
                    System.out.println(" " + cdf.getPermissions() + " " + cdf.getName() + end);
                }
            } else {
                for (DFTreeNode cdf : childrenNodes) {
                    String end = "";
                    if (!cdf.isFile)
                        end = "/";
                    System.out.print(" " + cdf.getName() + end);
                }
            }
        }
    }

    public static class CLI {

        static DFTreeNode currentDir, rootDirectory;
        static final String defaultPermissions = "rwxrwxrwx";

        private static void parseInput() {
            System.out.print(": ");
            Scanner scInput = new Scanner(System.in);
            String inputStr = scInput.nextLine();
            //
            // System.out.println("Command -> " + inputStr + " ");

            if (!inputStr.equals("exit")) {
                parseCommand(inputStr);
                parseInput();
            }
        }

        private static void parseCommand(String cmd) {
            String[] cmdParts = cmd.split(" ");
            switch (cmdParts[0]) {
                case "ls":
                    lsCommand(cmdParts);
                    break;

                case "pwd":
                    pwdCommand();
                    break;

                case "cd":
                    cdCommand(cmdParts);
                    break;

                case "mkdir":
                    mkdirCommand(cmdParts);
                    break;

                case "vi":
                    viCommand(cmdParts);
                    break;

                default:
                    System.out.println("Invalid command " + cmd);
            }
        }

        private static void lsCommand(String[] cmdArray) {
            if (cmdArray.length == 1)
                currentDir.printChildren(false);
            else
                currentDir.printChildren(true);
        }

        private static void pwdCommand() {

            DFTreeNode tempParentNode = currentDir.getParent();
            String path = currentDir.getName();
            while (tempParentNode != null) {
                path = tempParentNode.getName() + "'/'" + path;
                tempParentNode = tempParentNode.getParent();
            }

            System.out.println(path);
        }

        private static void viCommand(String[] cmdArray) {
            if (cmdArray.length == 1)
                System.out.println("Invalid VI command. Enter filename.");
            else
                try {
                    String permission = defaultPermissions;
                    if (cmdArray.length > 2)
                        permission = cmdArray[2];
                    currentDir.addChild(new DFTreeNode(cmdArray[1], false, true, permission, currentDir));
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        private static void mkdirCommand(String[] cmdArray) {
            if (cmdArray.length == 1)
                System.out.println("Invalid mkdir command. Enter directory name.");
            else
                try {
                    String permission = defaultPermissions;
                    if (cmdArray.length > 2)
                        permission = cmdArray[2];
                    currentDir.addChild(new DFTreeNode(cmdArray[1], false, false, permission, currentDir));
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        private static void cdCommand(String[] cmdArray) {
            if (cmdArray.length == 1)
                System.out.println("Invalid cd command.");
            else
                try {

                    switch (cmdArray[1]) {
                        case "/":
                            currentDir = rootDirectory;
                            break;

                        case "..":
                            currentDir = currentDir.getParent();
                            break;

                        default:
                            currentDir = currentDir.getChild(cmdArray[1]);
                            break;
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        }
    }

    public static void main(String[] args) throws Exception {

        CLI cli;
        try {
            cli = new CLI();
            cli.rootDirectory = new DFTreeNode("/", true, false, "rwxrwxrwx", null);
            cli.currentDir = cli.rootDirectory;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        cli.parseInput();
    }

}

