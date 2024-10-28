import java.io.IOException;
import java.nio.file.*;

public class DirectoryManager {

  private static final String ROOT = "data";

  public static void main(String[] args) throws IOException {
    try {

      Path root = Paths.get("data");
      try {
        Files.createDirectory(root);
        System.out.println("Creating folder on root 'data'.");
      } catch (FileAlreadyExistsException e) {
        System.out.println("Folder 'data' already exists.");
      }


      if (!Files.exists(root)) {
        Files.createDirectory(root);
        System.out.println("Creating folder on root 'data' safely.");
      } else {
        System.out.println("Folder 'data' on root already exists, skipping creation.");
      }


      Path subdir1 = root.resolve("subdir1");
      Path subdir2 = root.resolve("subdir2");
      Path subdir3 = root.resolve("subdir3");
      Path subsubdir = subdir3.resolve("subsubdir");

      Files.createDirectories(subdir1);
      System.out.println("Creating subfolder 'data/subdir1'.");
      Files.createDirectories(subdir2);
      System.out.println("Creating subfolder 'data/subdir2'.");
      Files.createDirectories(subsubdir);
      System.out.println("Creating subfolder 'subsubdir' inside 'data/subdir3'.");


      Path myFile = subsubdir.resolve("myfile.txt");
      Files.createFile(myFile);
      System.out.println("Creating the file 'myfile.txt' inside 'data/subdir3/subsubdir'.");

      Path myFileCopy = subdir1.resolve("myfile-copy.txt");
      Path myFileCopy1 =  Files.copy(myFile, myFileCopy, StandardCopyOption.REPLACE_EXISTING);
      System.out.println("Copying 'myfile.txt' to 'subdir1' as 'myfile-copy.txt'.");

      Path myFileCopy2 = subdir3.resolve("myfile-copy2.txt");
      Path myFileCopy3 = Files.copy(myFile, myFileCopy2, StandardCopyOption.REPLACE_EXISTING);
      System.out.println("Copying 'myfile.txt' to 'subdir3' as 'myfile-copy2.txt'.");

      Path myFileCopy4= Files.move(myFileCopy2, subdir2.resolve(myFileCopy2.getFileName()), StandardCopyOption.REPLACE_EXISTING);
      System.out.println("Moving 'myfile-copy2.txt' to 'subdir2'.");


      Files.delete(myFile);
      System.out.println("Deleting the original file 'myfile.txt'.");

      Files.delete(myFileCopy1);
      System.out.println("Deleting the original file 'myfile-copy.txt'.");

      Files.delete(myFileCopy4);
      System.out.println("Deleting the original file 'myfile2-copy.txt' from 'subdir2'.");

      // Delete the data folder entirely
      Files.deleteIfExists(subdir1);
      System.out.println("Deleting subfolder 'data/subdir1'.");
      Files.deleteIfExists(subdir2);
      System.out.println("Deleting subfolder 'data/subdir2'.");
      Files.deleteIfExists(subsubdir);
      System.out.println("Deleting subfolder 'data/subdir3/subsubdir'.");
      Files.deleteIfExists(subdir3);
      System.out.println("Deleting subfolder 'data/subdir3'.");
      Files.delete(root);
      System.out.println("Deleting the 'data' folder.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
