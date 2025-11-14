from PIL import Image

# Bildpfade anpassen
input_path = r"C:\Users\Franziska.Dold\Desktop\Programmierkurs2\Bushaltestellenanzeige\Haltestellenanzeige\Bushaltestellen-icon.png"  # Originalbild
output_path = r"C:\Users\Franziska.Dold\Desktop\Programmierkurs2\Bushaltestellenanzeige\Haltestellenanzeige\Bushaltestellen-icon_kleiner.png"            # Zielbild

# Bild öffnen, skalieren und speichern
with Image.open(input_path) as img:
    resized_img = img.resize((64, 64), Image.LANCZOS)
    resized_img.save(output_path, format="PNG")
